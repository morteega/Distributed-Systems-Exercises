import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.*;
import javax.servlet.ServletContext;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String method=(String)request.getParameter("task");
        PrintWriter out= response.getWriter();
        response.setContentType("text/html");
        if(method.equals("findStock")){
            String stockName=(String) request.getParameter("stock");
            Stocks stock=this.fund.getStockByName(stockName);
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Stock Found Succesfully<br></p>");
            out.println("<p>"+ stock.getName()+"<br>"+stock.getQuantity()+"<br>"+stock.getDividend()+"</p");
            out.println("<h3>\n" +
                                "            Back to Menu\n" +
                                "        </h3>\n" + 
                                "        <ul>\n" + 
                                "            <li><a href=\"index.html\">Menu</a></li>\n" + 
                                "        </ul>");
            out.flush();
        }else if(method.equals("findAllStocks")){
            List<Stocks> list=this.fund.getStocks();
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Stocks Found Succesfully<br></p>");
            for(int i=0;i<list.size();i++){
                out.println("<p><br>"+list.get(i).getName()+"<br>"+list.get(i).getQuantity()+"<br>"+list.get(i).getDividend());
            }
            out.println("<h3>\n" +
                                "            Back to Menu\n" +
                                "        </h3>\n" + 
                                "        <ul>\n" + 
                                "            <li><a href=\"index.html\">Menu</a></li>\n" + 
                                "        </ul>");
            out.flush();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        String method=(String)request.getParameter("task");
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        if("addStock".equals(method)){
            String stockName= (String) request.getParameter("stock");
            Double quantity=Double.parseDouble((String)request.getParameter("quantity"));
            Double dividend=Double.parseDouble((String)request.getParameter("dividend"));
            this.fund.addStock(stockName,dividend,quantity);
            out.println("<p>Stock added succesfully</p>");
            out.println("<h3>\n" +
                                "            Back to Menu\n" +
                                "        </h3>\n" + 
                                "        <ul>\n" + 
                                "            <li><a href=\"index.html\">Menu</a></li>\n" + 
                                "        </ul>");
            out.flush();
        }else if(method.equals("changeQuantity")){
            Double newQuantity=Double.parseDouble((String) request.getParameter("quantity"));
            String stockName=(String) request.getParameter("stock");
            Stocks stock=fund.getStockByName(stockName);
            stock.setQuantity(newQuantity);
            out.println("<p>Quantity changed succesfully</p>");
            out.println("<h3>\n" +
                                "            Back to Menu\n" +
                                "        </h3>\n" + 
                                "        <ul>\n" + 
                                "            <li><a href=\"index.html\">Menu</a></li>\n" + 
                                "        </ul>");
            out.flush();
        }
    }

}