package commands;

import entities.User;
import manager.Message;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;
import utils.InputChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class SaveTimeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(SaveTimeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of SaveTimeCommand");
        String page;
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        if (InputChecker.checkNumber(time)) {
            AssignmentsService service = ServiceFactory.getAssignmentsService();
            service.saveTime(((User) (request.getSession().getAttribute("user"))).getEmail(), description, time);
            page = PagesJsp.getInstance().getProperty(PagesJsp.CLIENT);
        } else {
            request.setAttribute("description", description);
            request.setAttribute("errorInputNumber", Message.INPUT_NUMBER_ERROR);
            page = PagesJsp.getInstance().getProperty(PagesJsp.SET_TIME);
        }
        request.setAttribute("currentPage", page);
        return page;

    }
}
