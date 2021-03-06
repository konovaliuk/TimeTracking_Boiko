package commands.sidebar.admin;

import commands.Command;
import manager.PagesJsp;
import org.apache.log4j.Logger;
import services.ServiceFactory;
import services.interfaces.AssignmentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mirosha
 */
public class CreateAssignCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(CreateAssignCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Execution of CreateAssignCommand");
        String page;
        String element = request.getParameter("request");
        String[] info = element.split(" ", 2);
        AssignmentsService service = ServiceFactory.getAssignmentsService();
        service.createAssignment(info[0], info[1]);
        page = PagesJsp.getInstance().getProperty(PagesJsp.ADMIN);
        request.setAttribute("currentPage", page);
        return page;
    }
}
