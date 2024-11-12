package iuh.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import iuh.dao.DienThoaiDAO;
import iuh.dao.NhaCungCapDAO;
import iuh.daoIml.DienThoaiDAOImpl;
import iuh.daoIml.NhaCungCapDAOIml;
import iuh.models.DienThoai;
import iuh.models.NhaCungCap;

import iuh.utils.EntityManagerFactoryUtils;

/**
 * Servlet implementation class DienThoaiServlet
 */
@MultipartConfig
@WebServlet(urlPatterns = "/dienThoai")
public class DienThoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EntityManagerFactoryUtils factoryUtils;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;
	private Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		factoryUtils = new EntityManagerFactoryUtils();
		dienThoaiDAO = new DienThoaiDAOImpl(factoryUtils.getEntityManager());
		nhaCungCapDAO = new NhaCungCapDAOIml(factoryUtils.getEntityManager());
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		this.factoryUtils.close();

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DienThoaiController() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
		System.out.println("Received GET request with action: " + action);

		switch (action) {
		case "new": {
			showAddForm(request, response);
			break;
		}
		case "edit": {
			showEditForm(request, response);
			break;
		}
		default:
			showList(request, response);
			break;
		}

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id + "");
			// Tìm điện thoại trong CSDL theo ID
			DienThoai dienThoai = dienThoaiDAO.findAllDienThoaiById(id);
			List<NhaCungCap> nhaCungCaps = nhaCungCapDAO.findAll();
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("nhaCungCaps", nhaCungCaps);
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/DienThoaiForm.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String maNCCString = request.getParameter("maNCC");
	        int maNCC = -1;
	        if (maNCCString != null && !maNCCString.isEmpty()) {
	            maNCC = Integer.parseInt(maNCCString);
	        }

	        List<NhaCungCap> nhaCungCaps = nhaCungCapDAO.findAll();
	        List<DienThoai> dienThoais;

	        request.setAttribute("nhaCungCaps", nhaCungCaps);
	        
	        // Lọc sản phẩm dựa trên nhà cung cấp nếu chọn
	        if (maNCC != -1) {
	            dienThoais = dienThoaiDAO.findAllDienThoaiByNCC(maNCC);
	        } else {
	            dienThoais = dienThoaiDAO.findAllDienThoai();
	        }
	        
	        request.setAttribute("dienThoais", dienThoais);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("views/DanhSachDienThoaiNCC.jsp");
	        dispatcher.forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private void showAddForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<NhaCungCap> nhaCungCaps = nhaCungCapDAO.findAll();
			request.setAttribute("nhaCungCaps", nhaCungCaps);
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/DienThoaiForm.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside doPost");
		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
		System.out.println("Received Post request with action: " + action);

		switch (action) {
		case "add": {
			add(request, response);
			break;
		}
		case "update": {
			update(request, response);
			break;
		}
		default:
			System.out.println("Lỗi");
			break;
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		DienThoai dienThoai = new DienThoai();
		try {
			String tenDienThoai = request.getParameter("tenDienThoai");
			System.out.println(tenDienThoai);
			String cauHinh = request.getParameter("cauHinh");
			System.out.println(cauHinh);
			int namSanXuat = Integer.parseInt(request.getParameter("namSanXuat"));
			Integer maNCC = Integer.parseInt(request.getParameter("maNCC"));

			dienThoai.setTenDienThoai(tenDienThoai);
			dienThoai.setCauHinh(cauHinh);
			dienThoai.setNamSanXuat(namSanXuat);
			
			
			// Lấy hình ảnh từ phần upload
			Part hinhAnhPart = request.getPart("hinhAnh");
			String hinhAnh = dienThoai.getHinhAnh(); // Lưu trữ đường dẫn hình ảnh cũ

			if (hinhAnhPart != null && hinhAnhPart.getSize() > 0) {
				String fileName = hinhAnhPart.getSubmittedFileName(); // Lấy tên file

				// Thiết lập đường dẫn tương đối
				String relativePath = "dataImgs"; // Đường dẫn tương đối
				String uploadPath = getServletContext().getRealPath(relativePath); // Lấy đường dẫn thực tế từ servlet
																					// context
				// Kiểm tra và tạo thư mục nếu chưa tồn tại
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // Tạo thư mục và tất cả các thư mục con nếu cần
				}

				// Lưu file vào server
				File uploadedFile = new File(uploadDir, fileName);
				hinhAnhPart.write(uploadedFile.getAbsolutePath()); // Lưu file vào đường dẫn đã chỉ định

				// Cập nhật đường dẫn hình ảnh trong đối tượng dienThoai
				dienThoai.setHinhAnh("/" + relativePath + "/" + fileName); // Đường dẫn để truy cập từ web
				System.out.println("Saved Image Name: " + fileName);
			} else {
				// Nếu không có file mới, giữ lại hình ảnh cũ
				dienThoai.setHinhAnh(hinhAnh);
			}

			// Tìm nhà cung cấp và thiết lập cho điện thoại
			NhaCungCap nhaCungCap = nhaCungCapDAO.findNCCByid(maNCC);
			if (nhaCungCap != null) {
				dienThoai.setNhaCungCap(nhaCungCap);
			} else {
				System.out.println("Not found NCC");
			}
			Set<ConstraintViolation<DienThoai>> violations = validator.validate(dienThoai);
			if (violations.isEmpty()) {
				dienThoaiDAO.save(dienThoai);
				response.sendRedirect(request.getContextPath() + "/dienThoai?action=list");
			} else {
				// Tạo map để lưu các lỗi theo từng trường
				Map<String, String> errors = new HashMap<String, String>();
				for (ConstraintViolation<DienThoai> violation : violations) {
					String field = violation.getPropertyPath().toString();
					String message = violation.getMessage();
					errors.put(field, message); // Thêm lỗi vào map
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("views/DienThoaiForm.jsp");
				request.setAttribute("errors", errors);
				request.setAttribute("dienThoai", dienThoai);
				dispatcher.forward(request, response);
			}

			

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Lấy các tham số từ form
			Integer id = Integer.parseInt(request.getParameter("id"));
			String tenDienThoai = request.getParameter("tenDienThoai");
			System.out.println(tenDienThoai);
			String cauHinh = request.getParameter("cauHinh");
			System.out.println(cauHinh);
			int namSanXuat = Integer.parseInt(request.getParameter("namSanXuat"));
			Integer maNCC = Integer.parseInt(request.getParameter("maNCC"));

			// Lấy thông tin điện thoại từ database theo id
			DienThoai dienThoai = dienThoaiDAO.findAllDienThoaiById(id);
			if (dienThoai == null) {
				System.out.println("Not found");
				return;
			}

			dienThoai.setTenDienThoai(tenDienThoai);
			dienThoai.setCauHinh(cauHinh);
			dienThoai.setNamSanXuat(namSanXuat);
			// Lấy hình ảnh từ phần upload
			Part hinhAnhPart = request.getPart("hinhAnh");
			String hinhAnh = dienThoai.getHinhAnh(); // Lưu trữ đường dẫn hình ảnh cũ

			if (hinhAnhPart != null && hinhAnhPart.getSize() > 0) {
				String fileName = hinhAnhPart.getSubmittedFileName(); // Lấy tên file

				// Thiết lập đường dẫn tương đối
				String relativePath = "dataImgs"; // Đường dẫn tương đối
				String uploadPath = getServletContext().getRealPath(relativePath); // Lấy đường dẫn thực tế từ servlet
																					// context
				// Kiểm tra và tạo thư mục nếu chưa tồn tại
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); // Tạo thư mục và tất cả các thư mục con nếu cần
				}

				// Lưu file vào server
				File uploadedFile = new File(uploadDir, fileName);
				hinhAnhPart.write(uploadedFile.getAbsolutePath()); // Lưu file vào đường dẫn đã chỉ định

				// Cập nhật đường dẫn hình ảnh trong đối tượng dienThoai
				dienThoai.setHinhAnh("/" + relativePath + "/" + fileName); // Đường dẫn để truy cập từ web
				System.out.println("Saved Image Name: " + fileName);
			} else {
				// Nếu không có file mới, giữ lại hình ảnh cũ
				dienThoai.setHinhAnh(hinhAnh);
			}

			// Tìm nhà cung cấp và thiết lập cho điện thoại
			NhaCungCap nhaCungCap = nhaCungCapDAO.findNCCByid(maNCC);
			if (nhaCungCap != null) {
				dienThoai.setNhaCungCap(nhaCungCap);
			} else {
				System.out.println("Not found");
			}

			Set<ConstraintViolation<DienThoai>> violations = validator.validate(dienThoai);
			if (violations.isEmpty()) {
				dienThoaiDAO.save(dienThoai);
				response.sendRedirect(request.getContextPath() + "/dienThoai?action=list");
			} else {
				// Tạo map để lưu các lỗi theo từng trường
				Map<String, String> errors = new HashMap<String, String>();
				for (ConstraintViolation<DienThoai> violation : violations) {
					String field = violation.getPropertyPath().toString();
					String message = violation.getMessage();
					errors.put(field, message); // Thêm lỗi vào map
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("views/DienThoaiForm.jsp");
				request.setAttribute("errors", errors);
				request.setAttribute("dienThoai", dienThoai);
				dispatcher.forward(request, response);
			}

			

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
