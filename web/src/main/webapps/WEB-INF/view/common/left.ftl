<#import "/spring.ftl" as spring/>
<#macro page  admin>
<div id="sidebar">
            <div id="sidebar-wrapper">
                <a href="<@spring.url '#'/>">
                    <img id="logo" src="<@spring.url '/assets/images/logo.png' />" width="100%" />
                </a>
                <div id="profile-links">
                    你好,
                    <a href="#"><#if session?exists>${session.userName}</#if></a>
                    <br />
                    <a href="#" target="_blank">楼口·<#if session?exists>${session.siteName}</#if></a>
                    &nbsp;|&nbsp;
                    <a href="/logout">注销</a>
                </div>
                <ul id="main-nav">
                <#list tabs as parentTab>
                    <li class="menu">
                        <a href="javascript:void(0);" class="nav-top-item">${parentTab.text}</a>
                        <ul class="sub-menu">
                          <#list parentTab.subtabs as subtab>
                            <li>
                                <a href="<@spring.url '${subtab.path}'/> " module-name="${parentTab.moduleName}">${subtab.text}</a>
                            </li>
                            </#list>
                        </ul>
                    </li>
                </#list>
                </ul>
            </div>
        </div>
</#macro>