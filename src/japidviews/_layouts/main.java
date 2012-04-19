package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/_layouts/main.html
// Change to this file will be lost next time the template file is compiled.
//
public abstract class main extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/_layouts/main.html";
	public main() {
		super(null);
	}
	public main(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {
		p("<html>\n" + 
"	<head>\n" + 
"		<title>Home</title>\n" + 
"		<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/styles.css\">\n" + 
"	</head>\n" + 
"	<body>\n" + 
"	");// line 1
		doLayout();// line 7
		p("	\n" + 
"	Date: ");// line 7
		date();p("\n" + 
"	</body>\n" + 
"</html>");// line 9
			}
	 protected void date() {};

	protected abstract void doLayout();
}