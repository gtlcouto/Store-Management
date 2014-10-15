/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import case2dtos.ProductEJBDTO;
import case2ejbs.POFacadeBean;
import case2ejbs.ProductFacadeBean;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author GCouto
 */
@WebServlet(name = "QRCodeTestServlet", urlPatterns = {"/QRCode"})
public class QRCodeTestServlet extends HttpServlet {
@EJB(name = "pfb")
private ProductFacadeBean pfb;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String qrcode = request.getParameter("qrc");
        ProductEJBDTO prod = pfb.getProdInfo(qrcode);
        OutputStream outStream = response.getOutputStream();
        response.setContentType("image/png");
        response.setContentLength(prod.getQrcodearray().length);
        try {
            outStream.write(prod.getQrcodearray());
            outStream.flush();
            
        } finally {            
            outStream.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
