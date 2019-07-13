package com.spring.mvc.controller;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.spring.mvc.pojo.User;
import com.spring.mvc.pojo.ValidaterPojo;
import com.spring.mvc.service.UserService;
import com.spring.mvc.view.PdfExportService;
import com.spring.mvc.view.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = null;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    public ModelAndView details(Long id) {
        User user = userService.getUser(id);
        //模型和视图
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/details");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        userService.insertUser(user);
        return user;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String, Object> updateUserName(Long id, String userName) {
        User user = userService.updateUserName(id, userName);
        boolean flag = user != null;
        String message = flag ? "更新成功" : "更新失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(Long id) {
        int result = userService.deleteUser(id);
        boolean flag = result == 1;
        String message = flag ? "删除成功" : "删除失败";
        return resultMap(flag, message);
    }

    @RequestMapping("/detailsForJson")
    public ModelAndView detailsForJson(Long id) {
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        modelAndView.setView(jsonView);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    //    @RequestMapping("/table")
    //    public ModelAndView table() {
    //        List<User> userList = userService.findUsers(null, null);
    //        ModelAndView modelAndView = new ModelAndView();
    //        modelAndView.setViewName("user/table");
    //        modelAndView.addObject("userList", userList);
    //        return modelAndView;
    //    }

    @RequestMapping("/table")
    public ModelAndView table() {
        // 访问模型层得到数据
        List<User> userList = userService.findUsers(null, null);
        // 模型和视图
        ModelAndView mv = new ModelAndView();
        // 定义模型视图
        mv.setViewName("user/table");
        // 加入数据模型
        mv.addObject("userList", userList);
        // 返回模型和视图
        return mv;
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam(value = "note", required = false) String note) {
        List<User> userList = userService.findUsers(userName, note);
        return userList;
    }

    @GetMapping("valid/page")
    public String validatePage() {
        return "user/pojo";
    }

    /**
     * 解析验证参数错误
     *
     * @param validaterPojo 需要验证的 POJO, 使用注解 @Valid 表示验证
     * @param errors        错误信息，它由 Spring MVC 通过验证 POJO 后自动填充
     * @return 错误信息
     */
    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidaterPojo validaterPojo, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError objectError : oes) {
            String key;
            String msg;
            //字段错误
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                key = fieldError.getField();
            } else {
                //非字段错误
                key = objectError.getObjectName();
            }
            //错误信息
            msg = objectError.getDefaultMessage();
            System.out.println("gggg " + msg);
            errMap.put(key, msg);
        }
        return errMap;
    }

    /**
     * 打开请求页面
     *
     * @return 字符串，指向页面
     */
    @GetMapping("/add")
    public String add() {
        return "/user/add";
    }

    /**
     * 新增用户
     *
     * @param user 通过 @RequestBody 注解得到 JSON 参数
     * @return 回填 id 后的用户信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user) {
        userService.insertUser(user);
        return user;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/format/form")
    public String showFormat() {
        return "user/formatter";
    }

    @PostMapping("/format/commit")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                      @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user) {
        return user;
    }

    //StringToCollectionConverter 自动调用 StringToUserConverter 进行列表转换
    //http://localhost:8080/user/convert_list?userList=1-user_name_1-user_note_555,2-user_name_2-user_note_666,
    // 3-user_name_3-user_note_777
    @GetMapping("/convert_list")
    @ResponseBody
    public List<User> list(List<User> userList) {
        return userList;
    }

    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName, String note) {
        //查询用户信息列表
        List<User> userList = userService.findUsers(userName, note);
        //定义 pdf 视图
        View view = new PdfView(exportService());
        ModelAndView modelAndView = new ModelAndView();
        //设置视图
        modelAndView.setView(view);
        //加入数据模型
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    private PdfExportService exportService() {
        //使用 Lambda 表达式自定义导出
        return ((model, document, writer, request, response) -> {

            try {
                //A4 纸张
                document.setPageSize(PageSize.A4);
                //标题
                document.addTitle("用户信息");
                //换行
                document.add(new Chunk("\n"));
                //表格， 3 列
                PdfPTable table = new PdfPTable(3);
                //单元格
                PdfPCell cell = null;
                //字体, 定义为蓝色加粗
                Font font = new Font();
                font.setColor(Color.BLUE);
                font.setStyle(Font.BOLD);
                //标题
                cell = new PdfPCell(new Paragraph("id", font));
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", font));
                //居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", font));
                //居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                //获取数据模型中的用户列表
                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                //在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, Object> resultMap(boolean success, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}
