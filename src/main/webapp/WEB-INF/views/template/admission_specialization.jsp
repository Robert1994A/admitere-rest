<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div class="panel panel-default border-grey"
	id="admission-session-container">
	<div class="panel-heading">
		<h4 class="panel-title">Admission specialization</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<button type="button" class="btn btn-primary btn-icon"
				ui-sref=".statistics()">
				Statistics <i class="icon-stats-bars2"></i>
			</button>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<button type="button" class="btn btn-primary btn-icon"
					ui-sref=".users()">
					Users <i class="icon-users4"></i>
				</button>
			</security:authorize>
		</div>
	</div>
</div>