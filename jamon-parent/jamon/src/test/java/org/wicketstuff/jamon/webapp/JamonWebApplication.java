/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.jamon.webapp;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.jamon.component.JamonTestUtil;
import org.wicketstuff.jamon.request.cycle.JamonAwareRequestCycleListener;

/**
 * WebApplication so Jamon Monitoring can be tested.
 * 
 * @author lars
 *
 */
public class JamonWebApplication extends WebApplication
{
	public JamonWebApplication(int monitorsToStart)
	{
		JamonTestUtil.startThisManyMonitorsWithDelay(monitorsToStart, 15);
	}

	public JamonWebApplication()
	{
		this(10);
	}

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	protected void init()
	{
		super.init();

		getRequestCycleListeners().add(new JamonAwareRequestCycleListener(this, true));
	}
}
