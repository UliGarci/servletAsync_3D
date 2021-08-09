package mx.edu.utez.controller;

import com.google.gson.Gson;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.model.game.BeanGame;
import mx.edu.utez.model.game.DaoGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "ServletGame", urlPatterns = {"/readGames","createGame","updateGame"})
public class ServletGame extends HttpServlet {
    private Map map = new HashMap();
    final private Logger CONSOLE = LoggerFactory.getLogger(ServletGame.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("session")!=null) {
            //request.setAttribute("listgames", new DaoGame().findAll());
            //request.getRequestDispatcher("views/game/games.jsp").forward(request, response);
            map.put("listgames",new DaoGame().findAll());
            write(response,map);
        }else{
            request.getRequestDispatcher("/").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        BeanGame beanGame = new BeanGame();
        DaoGame daoGame = new DaoGame();
        BeanCategory beanCategory = new BeanCategory();
        switch (action) {
            case "agregar":
                Part part = request.getPart("image");
                InputStream image = part.getInputStream();

                beanGame.setNombregame(request.getParameter("nombregame"));
                beanGame.setDatepremiere(request.getParameter("datepremiere"));
                beanGame.setCategory_idcategory(beanCategory);

                boolean flag = daoGame.agregar(beanGame,image);
                if (flag){
                    map.put("message","se ha registrado correctamente");
                }else{
                    map.put("message","No se registro correctamente");
                }
                break;

            case "actualizar":
                beanCategory.setIdcategory(Integer.parseInt(request.getParameter("idCategory")));
                beanGame.setIdgame(Integer.parseInt("idGame"));
                beanGame.setNombregame(request.getParameter("nombregame"));
                beanGame.setDatepremiere(request.getParameter("date"));
                beanGame.setCategory_idcategory(beanCategory);
                boolean flag1=daoGame.actualizar(beanGame);
                if (flag1){
                    CONSOLE.error("Se ha actualizado correctamente");
                }else{
                    CONSOLE.error("No se actualizo correctamente");
                }
                break;
            case "eliminar":
                if (new DaoGame().eliminar(Integer.parseInt(request.getParameter("idgame")))){
                    //true
                }else {
                    //false
                }
                break;
            default:
                request.getRequestDispatcher("/").forward(request,response);
                break;
        }
        response.sendRedirect(request.getContextPath()+"/readGames");

    }
    private void write(HttpServletResponse response, Map <String,Object>map) throws IOException{
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(map));
    }
}
