<div><a href="/ndovado/login/controller">Login</a> - <a href="/ndovado/utente/controller">Registrazione</a></div>
<c:if test="${utenteBean.id != null || utenteBean >= 0}">
<div>Utente connesso: ${utenteBean.cognome} ${utenteBean.nome} - Ruolo ${utenteBean.ruolo}</div>
</c:if>

<hr />