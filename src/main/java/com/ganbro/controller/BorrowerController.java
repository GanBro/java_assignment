//package com.ganbro.controller;
//
//import com.ganbro.domain.common.Result;
//import com.ganbro.domain.entity.Borrower;
//import com.ganbro.service.BorrowerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/borrowers")
//@RequiredArgsConstructor
//public class BorrowerController {
//
//    private final BorrowerService borrowerService;
//
//    // 获取所有借阅人员信息
//    @GetMapping
//    public Result<List<Borrower>> getAllBorrowers() {
//        List<Borrower> borrowers = borrowerService.getAllBorrowers();
//        return Result.success(borrowers);
//    }
//
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
//}
