package cn.jrjzx.supervision.smallloan.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import cn.jrjzx.supervision.smallloan.view.FormPdfview;

/**
 * Created by aboullaite on 2017-02-24.
 */
public class PdfViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
    	if(s.endsWith(".pdf")){
    		System.out.println(s);
    		FormPdfview view = new FormPdfview();
    		return view;
    		
    	}
    	return null;
    }
}
