package com.modular.wishlist.query.api

import com.modular.common.annotation.MemberId
import com.modular.wishlist.query.api.dto.GetWishlistResponse
import com.modular.wishlist.query.executor.GetWishlistExecutor
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "위시리스트")
@RestController
@RequestMapping("/api/v1/wishlist")
class WishlistQueryController(
    private val getWishlistExecutor: GetWishlistExecutor
) {

    @Operation(summary = "위시리스트 조회")
    @GetMapping("")
    fun getWishlist(
        @MemberId memberId: Long,
        @RequestParam page: Int,
        @RequestParam pageSize: Int,
    ): ResponseEntity<GetWishlistResponse> {
        val result = getWishlistExecutor.execute(memberId, page, pageSize)
        return ResponseEntity.ok(GetWishlistResponse.from(result))
    }
}