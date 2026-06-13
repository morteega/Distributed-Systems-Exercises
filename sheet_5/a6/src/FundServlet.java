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
            this.fund= new Funds("fund1", new ArrayList<Stocks>(List.of(new Stocks("Apple", 0, 0),
            new Stocks("Nvidia",0,0))));
            context.setAttribute("fund", this.fund);
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String task= (String) request.getParameter("task");
        String stockName=(String) request.getParameter("stock");
        Stocks stock= fund.getStockByName(stockName);
        if("findStock".equals(task)){
            response.setContentType("text/html");
            PrintWriter out= response.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Stock searched</h1>");
            out.println("<p>"+ stock.getName()+"\n"+ stock.getQuantity()+ "\n"+ stock.getDividend()+"</p>");
            out.println("</body>");
            out.println("</hmtl>");
        }
        
    }
    
}
