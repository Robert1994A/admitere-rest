<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default border-grey" id="faculty-container">
	<div class="panel-heading">
		<h4 class="panel-title">{{faculty.name}}</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			<p>{{faculty.id}}</p>
			<p>{{faculty.name}}</p>
			<p>{{faculty.url}}</p>
			<p>{{faculty.description}}</p>
			<button type="button" ui-sref=".domains()"
				class="btn btn-primary btn-xlg">
				<i class="icon-comment-discussion position-left"></i> View domains
			</button>
			<button type="button" ui-sref=".sessions()"
				class="btn btn-primary btn-xlg">
				<i class="icon-comment-discussion position-left"></i> View sessions
			</button>
		</div>
	</div>
</div>