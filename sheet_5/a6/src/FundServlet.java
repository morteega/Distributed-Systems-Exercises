import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/Funds")
public class FundServlet extends HttpServlet{
    private Funds fund;

    public void init() throws ServletException{
        ServletContext context= getServletContext();
        this.fund=(Funds) context.getAttribute("fund");
        if(fund==null){
            this.fund= new Funds("fund1", new ArrayList<Stocks>(List.of(new Stocks("Apple", 15, 50),
            new Stocks("Nvidia",0,0))));
            context.setAttribute("fund", this.fund);
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method= (String) request.getParameter("task");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        if(method.equals("findStock")){
            String sotckName=(String) request.getParameter("stock");
            Stocks stock=fund.getStockByName(sotckName);
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Stock Found Succesfully<br></p>");
            out.println("<p>"+ stock.getName()+"<br>"+stock.getQuantity()+"<br>"+stock.getDividend()+"</p");
            out.flush();
        }else if(method.equals("addStock")){
            String sotckName= (String) request.getParameter("stock");
            double quantity=Double.parseDouble(request.getParameter("quantity"));
            double dividend=Double.parseDouble(request.getParameter("dividend"));
            fund.addStock(sotckName, dividend, quantity);
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Stock added succesfully</p>");
            out.flush();
        }
        
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String method= (String) request.getParameter("task");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        if(method.equals("addStock")){
            String sotckName= (String) request.getParameter("stock");
            double quantity=Double.parseDouble(request.getParameter("quantity"));
            double dividend=Double.parseDouble(request.getParameter("dividend"));
            fund.addStock(sotckName, dividend, quantity);
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Stock added succesfully</p>");
            out.flush(); 
        }
    }
    
}
