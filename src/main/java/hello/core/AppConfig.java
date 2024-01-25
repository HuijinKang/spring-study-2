package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java -> Spring
 * */
//리팩토링 후
@Configuration //스프링 설정 정보 어노테이션
public class AppConfig {

    @Bean //스프링 컨테이너에 등록
    public MemberService memberService() { //메서드명을 스프링 빈의 이름으로 사용
        return new MemberServiceImpl(memberRepository()); //@Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다
    }

    @Bean
    public static MemberRepository memberRepository() { //애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있는 장점
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public static DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

/**
 * AppConfig를 통해서 관심사를 확실하게 분리했다.
 * 구체화를 참조하는 건 AppConfig에서 담당한다
 * AppConfig는 공연 기획자다.
 * AppConfig는 구성 영역
 * */
//리팩토링 전
//public class AppConfig {
//
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    private static MemberRepository memberRepository() { //애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있는 장점
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    private static DiscountPolicy discountPolicy() {
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }
//}
