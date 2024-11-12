package iuh.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class UploadController
 */
@WebServlet(urlPatterns = "/Upload")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("") + File.separator + "uploads"; // Đường dẫn để lưu file
        File uploadDir = new File(path);
        if (!uploadDir.exists()) uploadDir.mkdir(); // Tạo thư mục nếu không tồn tại

        for (Part part : request.getParts()) { // Duyệt từng phần của request
            String fileName = extractFileName(part);
            part.write(uploadDir + File.separator + fileName); // Lưu file
        }

        // Lưu đường dẫn vào cơ sở dữ liệu hoặc thực hiện các hành động khác...
    }

    private String extractFileName(Part part) { // Trích xuất tên file từ part
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 2, cd.length() - 1);
            }
        }
        return null;
    }
}
