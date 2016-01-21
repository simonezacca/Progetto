package com.ndovado.tecservices.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import com.ndovado.tecservices.loggers.AppLogger;

public abstract class GenericMapper<B,M> {

		protected static DozerBeanMapper mapper;
		
		
		private List<String> myMappingFiles = new ArrayList<String>();
		
		public GenericMapper() {
			AppLogger.debug("inizializzo Dozer");
			myMappingFiles.add("dozerMapping.xml");
			mapper = new DozerBeanMapper();
			mapper.setMappingFiles(myMappingFiles);
		}
		
		public abstract B getBeanFromModel(M model);
		
		public abstract M getModelFromBean(B bean);
		          
}
