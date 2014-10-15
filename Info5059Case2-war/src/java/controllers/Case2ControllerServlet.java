/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLInputFactory;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
/**
 *
 * @author Gustavo
 * Revision 5/11/2013 - Updated to retrieve webpages from xml using stax
 */
@WebServlet(name = "Case2ControllerServlet", urlPatterns = {"/C2Control"},
        initParams = {@WebInitParam(name="Webpages",value="/WebPages.xml")})
public class Case2ControllerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.invalidate();
        String jsf = request.getParameter("jsf");
        try {
            String fileName = getServletConfig().getInitParameter("Webpages");
            ServletContext application = getServletConfig().getServletContext();
            InputStream in = application.getResourceAsStream(fileName);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader parser = inputFactory.createXMLStreamReader(in);
            QName qWebpage = new QName("webpage");
            QName qUrl = new QName("url");
            QName qId = new QName("id");
            while (true) {
                int event = parser.next();
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    parser.close();
                    break;
                }

                if (event == XMLStreamConstants.START_ELEMENT) {

                    if (parser.getName().getLocalPart().equals(qWebpage.getLocalPart())) {
                        if(parser.getAttributeValue(null, qId.getLocalPart()).equals(jsf)) {
                            String url = parser.getAttributeValue(null, qUrl.getLocalPart());
                            response.sendRedirect(response.encodeRedirectURL(url));
                            break;
                        }
                    }
                }
            }

            parser.close();
            
            
        } catch(Exception e) {
            System.out.print(e.getMessage());
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
