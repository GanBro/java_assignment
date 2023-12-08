package com.ganbro.controller;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.entity.User;
import com.ganbro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {

    private final UserService userService;

    @GetMapping("/searchByPage")
    public Result<PageData<User>> searchUsersByPage(@RequestParam("query") String query,
                                                    @RequestParam("pageNum") int pageNum,
                                                    @RequestParam("pageSize") int pageSize) {
        PageData<User> pageData = userService.searchUsersByPage(query, pageNum, pageSize);
        return Result.success(pageData);
    }
//    // 获取借阅人员详细信息
//    @GetMapping("/{borrowerId}")
//    public Result<Borrower> getBorrowerDetails(@PathVariable Long borrowerId) {
//        Borrower borrower = borrowerService.getBorrowerDetails(borrowerId);
//        return Result.success(borrower);
//    }
//
//    // 添加借阅人员信息
//    @PostMapping
//    public Result<Void> addBorrower(@RequestBody BorrowerRequest borrowerRequest) {
//        borrowerService.addBorrower(borrowerRequest);
//        return Result.success(null);
//    }
//
//    // 修改借阅人员信息
//    @PutMapping("/{borrowerId}")
//    public Result<Void> updateBorrower(@PathVariable Long borrowerId, @RequestBody BorrowerRequest borrowerRequest) {
//        borrowerService.updateBorrower(borrowerId, borrowerRequest);
//        return Result.success(null);
//    }
//
//    // 删除借阅人员信息
//    @DeleteMapping("/{borrowerId}")
//    public Result<Void> deleteBorrower(@PathVariable Long borrowerId) {
//        borrowerService.deleteBorrower(borrowerId);
//        return Result.success(null);
//    }
}
