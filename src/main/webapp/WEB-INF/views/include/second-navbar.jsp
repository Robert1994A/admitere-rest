<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!-- Second navbar -->
<div class="navbar navbar-default" id="navbar-second">
	<ul class="nav navbar-nav no-border visible-xs-block">
		<li><a class="text-center collapsed" data-toggle="collapse"
			data-target="#navbar-second-toggle"><i class="icon-menu7"></i></a></li>
	</ul>

	<div class="navbar-collapse collapse" id="navbar-second-toggle">
		<ul class="nav navbar-nav">
			<li><a ui-sref="home()"><i class="icon-home5 position-left"></i>
					Home</a></li>
			<security:authorize access="isAuthenticated()">
				<li><a ui-sref="universities()"><i
						class="icon-graduation position-left"></i> Universities</a></li>
				<li><a ui-sref="appliedSessions()"><i
						class="icon-list position-left"></i> Applied sessions </a></li>
				<li><a ui-sref="profile()"><i
						class="icon-profile position-left"></i> Profile</a></li>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a ui-sref="users()"><i
							class="icon-users4 position-left"></i> Users </a></li>
				</security:authorize>
			</security:authorize>
			<li><a ui-sref="about()"><i class="icon-info3 position-left"></i>
					About </a></li>
			<li><a ui-sref="contact()"><i
					class="icon-envelop3 position-left"></i> Contact </a></li>
			<security:authorize access="!isAuthenticated()">
				<li><a ui-sref="login()"><i
						class="icon-arrow-right6 position-left"></i> Login </a></li>
			</security:authorize>
		</ul>
	</div>
</div>
<!-- /second navbar -->