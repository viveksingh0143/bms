<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="base" scope="page"/>

<ul class="nav nav-sidebar">
	<li><a href="#" data-toggle="offcanvas" class="visible-xs"><i
			class="fa fa-chevron-right"></i></a></li>
</ul>
<ul class="nav nav-sidebar">
	<li class="active"><a href="${base}"><i
			class="fa fa-th-large text-center"></i><span class="a-text hidden-xs"><spring:message
					code="nav.dashboard" /></span></a></li>
	<li class="dropdown"><a href="#"><i class="fa fa-edit"></i><span
			class="a-text hidden-xs"><spring:message code="nav.reports" /></span></a>
		<ul>
			<li><a href=""><i class="fa fa-list-alt"></i><span
					class="a-text hidden-xs"><spring:message code="nav.list" /></span></a></li>
			<li><a href=""><i class="fa fa-plus-square"></i><span
					class="a-text hidden-xs"><spring:message code="nav.new" /></span></a></li>
		</ul></li>
	<li class="dropdown"><a href="#"><i class="fa fa-users"></i><span
			class="a-text hidden-xs"><spring:message code="nav.users" /></span></a>
		<ul>
			<li><a href="${base}/users/"><i class="fa fa-list-alt"></i><span
					class="a-text hidden-xs"><spring:message code="nav.list" /></span></a></li>
			<li><a href="${base}/users/new"><i class="fa fa-plus-square"></i><span
					class="a-text hidden-xs"><spring:message code="nav.new" /></span></a></li>
		</ul></li>
	<li><a href="#"><i class="fa fa-archive"></i><span
			class="a-text hidden-xs">Export</span></a></li>
</ul>