<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!-- Main navigation bar -->
<div class="navbar navbar-inverse">
	<div class="navbar-header">
		<a class="navbar-brand" ui-sref="home()"><img
			src="<c:url value="/resources/images/logo_light.png"/>"></a>
		<ul class="nav navbar-nav pull-right visible-xs-block">
			<li><a data-toggle="collapse" data-target="#navbar-mobile"><i
					class="icon-tree5"></i></a></li>
		</ul>
	</div>

	<div class="navbar-collapse collapse" id="navbar-mobile">
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown language-switch"><a class="dropdown-toggle"
				data-toggle="dropdown"> <img
					src="<c:url value="/resources/images/flags/gb.png"/>"
					class="position-left" alt=""> English <span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<li><a class="deutsch"><img
							src="<c:url value="/resources/images/flags/de.png"/>" alt="">
							Deutsch</a></li>
					<li><a class="english"><img
							src="<c:url value="/resources/images/flags/gb.png"/>" alt="">
							English</a></li>
					<li><a class="espana"><img
							src="<c:url value="/resources/images/flags/es.png"/>" alt="">Español</a></li>
				</ul></li>
			<security:authorize access="isAuthenticated()">
				<li class="dropdown dropdown-user"><a class="dropdown-toggle"
					data-toggle="dropdown"> <img
						src="<c:url value="/resources/images/face9.jpg"/>" alt=""> <span><security:authentication
								property="principal.username" /> </span> <i class="caret"></i>
				</a>
					<ul class="dropdown-menu dropdown-menu-right">
						<li><a ui-sref="profile()"><i class="icon-user-plus"></i>
								My profile</a></li>
						<li><a ui-sref="account()"><i class="icon-cog5"></i> My
								account</a></li>
						<li><a id="logout-button" href="<c:url value="/logout"/>"><i class="icon-switch2"></i>
								Logout</a></li>
					</ul></li>
			</security:authorize>
		</ul>
	</div>
</div>
<!-- /main navigation bar -->