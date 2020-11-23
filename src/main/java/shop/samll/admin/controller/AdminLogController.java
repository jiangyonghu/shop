package shop.samll.admin.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.samll.admin.annotation.RequiresPermissionsDesc;
import shop.samll.core.util.ResponseUtil;
import shop.samll.core.validator.Order;
import shop.samll.core.validator.Sort;
import shop.samll.db.service.LogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/log")
@Api(tags = "日志管理")
@Validated
public class AdminLogController {
    private final Log logger = LogFactory.getLog(AdminLogController.class);

    @Autowired
    private LogService logService;

    @RequiresPermissions("admin:log:list")
    @RequiresPermissionsDesc(menu={"系统管理" , "操作日志"}, button="查询")
    @ApiOperation(value = "操作日志查询", notes = "操作日志查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<shop.samll.db.domain.PO.Log> logList = logService.querySelective(name, page, limit, sort, order);
        long total = PageInfo.of(logList).getTotal();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", logList);

        return ResponseUtil.ok(data);
    }
}
