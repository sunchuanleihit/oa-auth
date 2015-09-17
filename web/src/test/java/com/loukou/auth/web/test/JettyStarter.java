package com.loukou.auth.web.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyStarter {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8088);

		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		context.setDescriptor("src/main/webapps/WEB-INF/web.xml");
		ResourceCollection resources = new ResourceCollection(new String[] {
				"src/main/webapps/", "src/main/resources/" });
		context.setBaseResource(resources);
		context.setParentLoaderPriority(true);
		server.setHandler(context);

		server.start();
		server.join();
	}

}
