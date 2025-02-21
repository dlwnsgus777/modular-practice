package com.modular.wishlist.command.executor

import com.modular.fixture.member.MemberFixture
import com.modular.fixture.product.ProductFixture
import com.modular.member.command.domain.repository.MemberRepository
import com.modular.product.command.domain.repository.ProductRepository
import com.modular.wishlist.WishlistRepository
import com.modular.wishlist.command.domain.service.WishlistService
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@Transactional
@SpringBootTest
class AddWishListExecutorTest {

    @Autowired
    lateinit var addWishListExecutor: AddWishListExecutor

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var wishlistRepository: WishlistRepository

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Test
    @DisplayName("위시리스트 추가")
    fun addWishlist01() {
        // given
        val password = "password"
        val member = MemberFixture.aMember(password = password)
        val saveMember = memberRepository.save(member)

        val product = ProductFixture.aProduct(productName = "상품1")
        productRepository.save(product)

        entityManager.flush()

        // when
        val savedId = addWishListExecutor.execute(saveMember.id!!, product.id!!)

        // then
        val savedWishlist = wishlistRepository.findById(savedId)
        assertThat(savedWishlist).isNotNull
        assertThat(savedWishlist!!.memberId).isEqualTo(saveMember.id!!)
    }

}