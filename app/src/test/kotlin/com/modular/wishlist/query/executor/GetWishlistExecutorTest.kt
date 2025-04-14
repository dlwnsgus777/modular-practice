package com.modular.wishlist.query.executor

import com.modular.fixture.member.MemberFixture
import com.modular.fixture.product.ProductFixture
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.Wishlist
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class GetWishlistExecutorTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var wishlistRepository: WishlistRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var getWishlistExecutor: GetWishlistExecutor

    @Test
    @DisplayName("회원의 위시리스트 조회")
    fun getWishlist01() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        val saveMember = memberRepository.save(member)
        entityManager.flush()

        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)

        wishlistRepository.save(
            Wishlist(
                memberId = saveMember.id!!,
                productId = product.id!!
            )
        )
        val page = 0
        val pageSize = 10

        // when
        val result = getWishlistExecutor.execute(saveMember.id!!, page, pageSize)

        // then
        assertThat(result).isNotNull
        assertThat(result.content).isNotEmpty()
        assertThat(result.content[0].productName).isEqualTo("상품5")
        assertThat(result.totalElements).isEqualTo(1)
    }

}