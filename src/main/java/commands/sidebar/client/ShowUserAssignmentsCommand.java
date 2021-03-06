package commands.sidebar.client;

import commands.Command;
import commands.NoCommand;
import commands.utils.Paginator;
import entities.Assignment;
import entities.User;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mirosha
 */
public class ShowUserAssignmentsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of ShowUserAssignmentsCommand");
        final int recordsPerPage = 5;
        String page;
        String email = ((User) request.getSession().getAttribute("user")).getEmail();
        String language = (String) request.getSession().getAttribute("language");
        AssignmentsService service = ServiceFactory.getAssignmentsService();
        Paginator paginator = new Paginator(service.getCountForUser(email, true), recordsPerPage);
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            paginator.setCurrentPage(Integer.valueOf(pageParameter));
        }
        List<Assignment> assignments = service.getUserAssignmentsPerPage(email, language, true,
                paginator.getCurrentPage(), recordsPerPage);
        request.setAttribute("userAssignments", assignments);
        request.setAttribute("pagesCount", paginator.getPagesCount());
        String choose = request.getParameter("choose");
        if (choose == null) {
            page = PagesJsp.getInstance().getProperty(PagesJsp.USER_ASSIGNMENTS);
        } else {
            page = PagesJsp.getInstance().getProperty(PagesJsp.ASSIGNMENTS_TO_DELETE);
        }
        request.setAttribute("currentPage", page);
        return page;
    }
}
