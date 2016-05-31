package com.sample.common.listener;

import java.util.Collections;
import java.util.List;

import com.google.inject.Module;
import com.sample.common.module.MyServletModule;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;

public class MyGuiceListener extends JerseyGuiceServletContextListener  {

	@Override
	protected List<? extends Module> modules() {
		return Collections.singletonList(new MyServletModule());
	}
}
