package com.suge.controller;

import com.suge.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * @author suu
 * @create 2020-12-14 0:37
 */
@MultipartConfig()
public class FileServlet extends BaseServlet{


    public void upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 返回Web应用程序文档根目录
        String path = this.getServletContext().getRealPath("/static/img");


        Part p = req.getPart("fileName");
        String message="";
        // 上传的文件不能超过3MB大小
        if(p.getSize() >1024*1024*1024){
            p.delete();
            message = "文件太大，不能上传！";
        }else{
            // 文件存储在文档根目录下member子目录中会员号子目录中
//            path = path + "\\static\\img";

            File f = new File(path);
            // 若目录不存在，则创建目录
            if( !f.exists()){
                f.mkdirs();
            }

            // 获得username作为文件名
            User user = (User) req.getSession().getAttribute("user");

            // 将上传的文件写入磁盘
            p.write(path + "\\"+ user.getUsername()+".jpg");
            message = "文件上传成功！";
        }
        req.getSession().setAttribute("uploadMessage",message);
        resp.sendRedirect(req.getContextPath()+"/index.jsp");

    }

    public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = this.getServletContext().getRealPath("/static/img");

        // 获得username作为文件名
        User user = (User) req.getSession().getAttribute("user");

        File file = new File(path,user.getUsername()+".jpg");
        if (file.exists()) {
            // 设置响应的内容类型为
            resp.setContentType("image/jpeg");

            // 设置Content-Disposition响应头，指定文件名
            resp.addHeader("Content-Disposition", "attachment;filename="+user.getUsername()+".jpg");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);  // 创建文件输入流
                bis = new BufferedInputStream(fis);
                // 返回输出流对象
                OutputStream os = resp.getOutputStream();
                // 读取1K的字节
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException ex) {
                System.out.println (ex.toString());
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
        }else{
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("文件不存在！");
        }

    }

}
