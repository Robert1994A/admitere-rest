<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default border-grey" id="university-container">
	<div class="panel-heading">
		<h4 class="panel-title">{{university.name}}</h4>
	</div>
	<div class="panel-body">
		<div class="row">
			{{university.id}} {{university.name}} {{university.description}}
			{{university.url}} {{university.contactInformation.email}}
			{{university.contactInformation.phoneNumber}}
			<button type="button" ui-sref=".faculties()"
				class="btn btn-primary btn-xlg">
				<i class="icon-comment-discussion position-left"></i> View faculties
			</button>
		</div>
	</div>
</div>