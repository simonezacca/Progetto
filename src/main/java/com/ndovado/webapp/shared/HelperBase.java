package com.ndovado.webapp.shared;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public abstract class HelperBase {

	protected enum SessionData {

		READ, IGNORE
	};

	private Method methodDefault = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Logger logger;
	
	protected Map<String, Map<String, String>> checked = new HashMap<String, Map<String, String>>();
	protected Map<String, Map<String, String>> selected = new HashMap<String, Map<String, String>>();

	public HelperBase(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		logger = Logger.getLogger("bytesizebook.webdev");
		logger.setLevel(Level.DEBUG);
	}

	protected void doGet() throws ServletException, IOException {
		response.getWriter().print("The doGet method must be overridden" + " in the class that extends HelperBase.");
	}

	protected void doPost() throws ServletException, IOException {
		response.getWriter().print("The doPost method must be overridden" + " in the class that extends HelperBase.");
	}

	protected abstract void copyFromSession(Object helper);

	public void addHelperToSession(String name, SessionData state) {
		if (SessionData.READ == state) {
			Object sessionObj = request.getSession().getAttribute(name);
			if (sessionObj != null) {
				copyFromSession(sessionObj);
			}
		}
		request.getSession().setAttribute(name, this);
	}

	public void addHelperToSession(String name, boolean checkSession) {
		if (checkSession) {
			Object sessionObj = request.getSession().getAttribute(name);
			if (sessionObj != null) {
				copyFromSession(sessionObj);
			}
		}
		request.getSession().setAttribute(name, this);
	}

	protected String executeButtonMethod() throws ServletException, IOException {
		String result = "";
		methodDefault = null;
		// Look for the most extended class.
		Class clazz = this.getClass();
		Class enclosingClass = clazz.getEnclosingClass();
		while (enclosingClass != null) {
			clazz = this.getClass();
			enclosingClass = clazz.getEnclosingClass();
		}
		// Start looking for methods from the most extended class.
		// If not found there, then start looking at the base classes.
		try {
			result = executeButtonMethod(clazz, true);
		} catch (Exception ex) {
			writeError(request, response, "Button Method Error", ex);
			return "";
		}

		return result;
	}

	protected String executeButtonMethod(Class clazz, boolean searchForDefault)
			throws IllegalAccessException, InvocationTargetException {
		String result = "";
		// retrieve all the methods
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			ButtonMethod annotation = method.getAnnotation(ButtonMethod.class);
			// look for those with button method annotations
			if (annotation != null) {
				// look for the default method
				if (searchForDefault && annotation.isDefault()) {
					methodDefault = method;
				}
				// if the button name matches, execute the method
				if (request.getParameter(annotation.buttonName()) != null) {
					result = invokeButtonMethod(method);
					break;
				}
			}
		}
		// If the result is still empty, then the method was not found. Look in
		// the super class.
		if (result.equals("")) {
			Class superClass = clazz.getSuperclass();
			if (superClass != null) {
				result = executeButtonMethod(superClass, methodDefault == null);
			}
			// If no method was found in the super classes, then use the default
			if (result.equals("")) {
				if (methodDefault != null) {
					result = invokeButtonMethod(methodDefault);
				} else {
					logger.error("(executeButtonMethod) No default method " + "was specified, but one was needed.");
					result = "No default method was specified,.";
				}
			}
		}
		return result;
	}

	protected String invokeButtonMethod(Method buttonMethod) throws IllegalAccessException, InvocationTargetException {
		String resultInvoke = "Could not invoke method";
		try {
			resultInvoke = (String) buttonMethod.invoke(this, (Object[]) null);
		} catch (IllegalAccessException iae) {
			logger.error("(invoke) Button method is not public.", iae);
			throw iae;
		} catch (InvocationTargetException ite) {
			logger.error("(invoke) Button method exception", ite);
			throw ite;
		}
		return resultInvoke;
	}

	public void fillBeanFromRequest(Object data) {
		try {
			org.apache.commons.beanutils.BeanUtils.populate(data, request.getParameterMap());
		} catch (IllegalAccessException iae) {
			logger.error("Populate - Illegal Access.", iae);
		} catch (InvocationTargetException ite) {
			logger.error("Populate - Invocation Target.", ite);
		}
	}

	private void populateThrow(Object data) throws IOException, ServletException {
		try {
			org.apache.commons.beanutils.BeanUtils.populate(data, request.getParameterMap());
		} catch (IllegalAccessException iae) {
			logger.error("Populate - Illegal Access.", iae);
			writeError(request, response, "Populate - Illegal Access.", iae);
		} catch (InvocationTargetException ite) {
			logger.error("Populate - Invocation Target.", ite);
			writeError(request, response, "Populate - Invocation Target.", ite);
		}
	}

	static public void writeError(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, String title, Exception ex)
					throws IOException, ServletException {
		java.io.PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("  <head>");
		out.println("    <title>" + title + "</title>");
		out.println("  </head>");
		out.println("  <body>");
		out.println("<h2>" + title + "</h2>");
		if (ex.getMessage() != null) {
			out.println("    <h3>" + ex.getMessage() + "</h3>");
		}
		if (ex.getCause() != null) {
			out.println("    <h4>" + ex.getCause() + "</h4>");
		}
		StackTraceElement[] trace = ex.getStackTrace();
		if (trace != null && trace.length > 0) {
			out.print("<pre>");
		}
		ex.printStackTrace(out);
		out.println("</pre>");
		out.println("  </body>");
		out.println("</html>");
		out.close();
	}
	
	// For JSP access, use a map that is created from
	// the validation messages.
	Map<String, String> errorMap = new HashMap<String, String>();

	public void setErrors(Object data) {
		// Hibernate fills the array with validation messages
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Object>> validationMessages = validator.validate(data);
		
		// copy the messages to a map
		errorMap.clear();
		if (validationMessages.size() != 0) {
			for (Iterator<ConstraintViolation<Object>> iterator = validationMessages.iterator(); iterator.hasNext();) {
				ConstraintViolation<Object> msg = iterator.next();
				String property = msg.getPropertyPath().toString();
				String errorMessage = msg.getMessage();
				errorMap.put(property, errorMessage);
			}
		}
	}

	public boolean isValid(Object data) {
		setErrors(data);
		return errorMap.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errorMap;
	}

	public boolean isValidProperty(String name) {
		String msg = errorMap.get(name);
		return msg == null || msg.equals("");
	}
	
	protected void setCheckedAndSelected(Object data) {
		setCheckedAndSelected(data, data.getClass());
	}

	protected void setCheckedAndSelected(Object data, Class clazz) {
		Method[] allMethods = clazz.getDeclaredMethods();
		for (Method method : allMethods) {
			SetByAttribute propAnnotation = method.getAnnotation(SetByAttribute.class);
			// Look for methods with a SetByAttribute annotation
			if (propAnnotation != null) {
				String property = method.getName();
				java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("get(.+)");
				java.util.regex.Matcher matcher = pattern.matcher(property);
				// The annotation must be on an accessor.
				if (!matcher.matches()) {
					logger.error(property + " must be an accessor.");
				} else {
					property = matcher.group(1);
					property = property.substring(0, 1).toLowerCase() + property.substring(1);
					// clear the map for this element
					clearProperty(property, propAnnotation.type());
					// Call the accessor. If it is a multiple-valued property,
					// then
					// the return type is an array.
					if (method.getReturnType().isArray()) {
						Object[] result = (Object[]) invokeGetter(data, method);
						if (result != null) {
							for (Object obj : result) {
								addChoice(property, obj.toString(), (AttributeType) propAnnotation.type());
							}
						}
					} else {
						Object result = invokeGetter(data, method);
						if (result != null) {
							addChoice(property, result.toString(), (AttributeType) propAnnotation.type());
						}
					}
				}
			}
		}
		// If there is parent class, then set its values, too.
		// Hmmmm, why did I ignore the extended classes?
		Class parentClass = clazz.getSuperclass();
		if (parentClass != null) {
			setCheckedAndSelected(data, parentClass);
		}
	}

	protected Object invokeGetter(Object obj, Method method) {
		Object result = null;
		try {
			result = method.invoke(obj, (Object[]) null);
		} catch (IllegalAccessException iae) {
			logger.error("(invoke) Accessor needs public access", iae);
		} catch (InvocationTargetException ite) {
			logger.error("(invoke) Accessor threw an exception", ite);
		}
		return result;
	}

	public Map<String, Map<String, String>> getChecked() {
		return checked;
	}

	public Map<String, Map<String, String>> getSelected() {
		return selected;
	}

	public void addChecked(String group, String item) {
		if (checked.get(group) == null) {
			checked.put(group, new HashMap<String, String>());
		}
		checked.get(group).put(item, "checked");
	}

	public void addSelected(String list, String item) {
		if (selected.get(list) == null) {
			selected.put(list, new HashMap<String, String>());
		}
		selected.get(list).put(item, "selected");
	}

	public void addChoice(String list, String item, AttributeType type) {
		if (type == null) {
			return;
		}
		if (AttributeType.CHECKED == type) {
			addChecked(list, item);
		}
		if (AttributeType.SELECTED == type) {
			addSelected(list, item);
		}
	}

	public void clearProperty(String property, AttributeType type) {
		Map<String, String> propMap;
		if (AttributeType.CHECKED == type) {
			propMap = checked.get(property);
			if (propMap != null) {
				propMap.clear();
			}
		} else if (AttributeType.SELECTED == type) {
			propMap = selected.get(property);
			if (propMap != null) {
				propMap.clear();
			}
		}
	}

	public void clearMaps() {
		checked.clear();
		selected.clear();
	}
	
	public abstract void resetNullable();
}
