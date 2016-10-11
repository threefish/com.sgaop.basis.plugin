package com.sgaop.basis.plugin.views;

import com.sgaop.basis.mvc.Mvcs;
import com.sgaop.basis.mvc.view.View;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/9/5 0005
 * To change this template use File | Settings | File Templates.
 * 自定义的freemarker视图
 */
public class FreeMarkerView implements View {

    private static Configuration cfg = new Configuration(Configuration.getVersion());
    private final static String _suffix = ".html";

    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File(Mvcs.getSession().getServletContext().getRealPath("/_view_/ftl/")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(String path, HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            cfg.setSharedVariable("base", request.getContextPath());
            Template template = cfg.getTemplate(path + _suffix);
            Writer writer = new OutputStreamWriter(response.getOutputStream());
            template.process(data, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
