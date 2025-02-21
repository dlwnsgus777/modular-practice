package com.modular.wishlist.command.api

import com.modular.common.annotation.MemberId
import com.modular.wishlist.command.api.dto.AddWishListRequestV1
import com.modular.wishlist.command.executor.AddWishListExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "위시리스트")
@RestController
@RequestMapping("/api/v1/wishlist")
class AddWishListController(
    private val addWishListExecutor: AddWishListExecutor
) {

    @Operation(summary = "위시리스트 추가")
    @PostMapping("/add-product")
    fun addWishlist(
        @MemberId memberId: Long,
        @RequestBody addWishListRequestV1: AddWishListRequestV1
    ): ResponseEntity<Unit> {
        addWishListExecutor.execute(memberId, addWishListRequestV1.productId)
        return ResponseEntity.ok().build()
    }

}