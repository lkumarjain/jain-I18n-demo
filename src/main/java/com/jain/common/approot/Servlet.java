/* 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jain.common.approot;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;

@WebServlet(urlPatterns = "/*")
public class Servlet extends VaadinServlet {
    private static final long serialVersionUID = 2298654353565L;

	@Inject
    private Instance <RootProvider> applicationProvider;

	@SuppressWarnings("serial")
	private final SessionInitListener sessionInitListener = new SessionInitListener() {
        public void sessionInit(SessionInitEvent event) throws ServiceException {
            event.getService();
            final VaadinSession session = event.getSession();
            session.addUIProvider(applicationProvider.get());
        }
    };

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        getService().addSessionInitListener(sessionInitListener);
    }
}