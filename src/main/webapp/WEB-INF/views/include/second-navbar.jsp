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
			<li><a href="#!/"><i class="icon-home5 position-left"></i>
					Dashboard</a></li>
			<li><a href="#!/contact"><i
					class="icon-envelop3 position-left"></i> Contact </a></li>
			<li><a href="#!/universities"><i
					class="icon-graduation position-left"></i> Universities</a></li>
			<li><a href="#!/profile"><i
					class="icon-profile position-left"></i> Profile</a></li>
			<li><a href="#!/about"><i class="icon-info3 position-left"></i>
					About </a></li>
			<li><a href="#!/users"><i class="icon-users4 position-left"></i>
					Users </a></li>

			<security:authorize access="!isAuthenticated()">
				<li><a href="#!/login"><i
						class="icon-arrow-right6 position-left"></i> Login </a></li>
			</security:authorize>

			<!-- 
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="icon-strategy position-left"></i>
					Starter kit <span class="caret"></span>
			</a>
				<ul class="dropdown-menu width-200">
					<li class="dropdown-header">Basic layouts</li>
					<li class="dropdown-submenu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><i
							class="icon-grid2"></i> Columns</a>
						<ul class="dropdown-menu">
							<li class="dropdown-header highlight">Options</li>
							<li><a href="starters/1_col.html">One column</a></li>
							<li><a href="starters/2_col.html">Two columns</a></li>
							<li class="dropdown-submenu"><a href="#">Three columns</a>
								<ul class="dropdown-menu">
									<li class="dropdown-header highlight">Sidebar position</li>
									<li><a href="starters/3_col_dual.html">Dual sidebars</a></li>
									<li><a href="starters/3_col_double.html">Double
											sidebars</a></li>
								</ul></li>
							<li><a href="starters/4_col.html">Four columns</a></li>
						</ul></li>
					<li class="dropdown-submenu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"><i
							class="icon-paragraph-justify3"></i> Navbars</a>
						<ul class="dropdown-menu">
							<li class="dropdown-header highlight">Fixed navbars</li>
							<li><a href="starters/layout_navbar_fixed_main.html">Fixed
									main navbar</a></li>
							<li><a href="starters/layout_navbar_fixed_secondary.html">Fixed
									secondary navbar</a></li>
							<li><a href="starters/layout_navbar_fixed_both.html">Both
									navbars fixed</a></li>
						</ul></li>
					<li class="dropdown-header">Optional layouts</li>
					<li><a href="starters/layout_boxed.html"><i
							class="icon-align-center-horizontal"></i> Fixed width</a></li>
					<li><a href="starters/layout_sidebar_sticky.html"><i
							class="icon-flip-vertical3"></i> Sticky sidebar</a></li>
				</ul></li>
				-->
		</ul>
	</div>
</div>
<!-- /second navbar -->