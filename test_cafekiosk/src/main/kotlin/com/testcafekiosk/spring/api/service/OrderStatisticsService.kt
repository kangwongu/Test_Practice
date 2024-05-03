package com.testcafekiosk.spring.api.service

import com.testcafekiosk.spring.api.service.mail.MailService
import com.testcafekiosk.spring.domain.order.OrderRepository
import com.testcafekiosk.spring.domain.order.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OrderStatisticsService(
    private val mailService: MailService,
    private val orderRepository: OrderRepository,
) {

    fun sendOrderStatisticsMail(orderDate: LocalDate, email: String): Boolean {
        // 해당 일자에 결제완료된 주문들을 가져와서
        val orders = orderRepository.findOrdersBy(
            orderDate.atStartOfDay(),
            orderDate.plusDays(1).atStartOfDay(),
            OrderStatus.PAYMENT_COMPLETED
        )

        // 총 매출 합계를 계산하고
        val totalAmount = orders.sumOf { it.totalPrice }

        // 메일전송
        val result = mailService.sendMail("no-reply@cafekiosk.com", email, "[매출통계] $orderDate", "총 매출 합계는 ${totalAmount}입니다")
        if (!result) {
            throw IllegalStateException("매출 통계 메일 전송에 실패했습니다")
        }

        return true
    }
}