package japidviews;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/home.html
// Change to this file will be lost next time the template file is compiled.
//
public class home extends main
{	public static final String sourceTemplate = "japidviews/home.html";
	public home() {
		super(null);
	}
	public home(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"user", "date",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "java.util.Date",  };
public static final Object[] argDefaults= new Object[] {null,null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.home.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	private String user;
	private java.util.Date date;
	public String render(String user,java.util.Date date) {
		this.user = user;
		this.date = date;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);}
		 if (t != -1) System.out.println("[home] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final login _login1 = new login(getOut());

// -- end of the tag objects

//------
;// line 1
p("<div>\n" + 
"	<h1>");// line 3
		_login1.setOut(getOut()); _login1.render(user);// line 5
		p("!</h1>\n" + 
"</div>\n");// line 5
		
	}

	@Override protected void date() {
		p( date);;
	}
}