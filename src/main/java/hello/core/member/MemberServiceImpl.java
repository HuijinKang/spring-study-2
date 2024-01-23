package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //DIP를 위반하지 않음, Impl은 오로지 메소드를 호출만 함

    public MemberServiceImpl(MemberRepository memberRepository) { //생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
