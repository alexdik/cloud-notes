package notes.controller;

import cn.bran.japid.compiler.OpMode;
import cn.bran.japid.template.JapidRenderer;
import restdisp.worker.AbstractWorker;

public class AbstractController extends AbstractWorker {
	static {
		JapidRenderer.init(OpMode.dev, "src", 5);
	}

	public String render(String view, Object... model) {
		return JapidRenderer.renderWith(view, model);
	}
}
