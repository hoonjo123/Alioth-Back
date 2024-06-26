package com.alioth.server.common.domain;

import com.alioth.server.domain.answer.domain.Answer;
import com.alioth.server.domain.answer.dto.req.AnswerReqDto;
import com.alioth.server.domain.answer.dto.res.AnswerResDto;
import com.alioth.server.domain.board.domain.Board;
import com.alioth.server.domain.board.dto.req.BoardCreateDto;
import com.alioth.server.domain.board.dto.res.BoardResDto;
import com.alioth.server.domain.contract.domain.Contract;
import com.alioth.server.domain.contract.dto.req.ContractCreateDto;
import com.alioth.server.domain.contract.dto.res.ContractExcelResDto;
import com.alioth.server.domain.contract.dto.res.ContractResDto;
import com.alioth.server.domain.dummy.domain.ContractMembers;
import com.alioth.server.domain.dummy.domain.Custom;
import com.alioth.server.domain.dummy.domain.InsuranceProduct;
import com.alioth.server.domain.login.dto.res.LoginResDto;
import com.alioth.server.domain.member.domain.SalesMembers;
import com.alioth.server.domain.member.dto.req.SalesMemberCreateReqDto;
import com.alioth.server.domain.member.dto.res.SalesMemberResDto;
import com.alioth.server.domain.schedule.domain.Schedule;
import com.alioth.server.domain.schedule.dto.req.ScheduleReqDto;
import com.alioth.server.domain.schedule.dto.res.ScheduleResDto;
import com.alioth.server.domain.team.domain.Team;
import com.alioth.server.domain.team.dto.TeamResDto;
import com.alioth.server.domain.team.dto.TeamReqDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeChange {

    // 사원
    public LoginResDto memberToLoginResDto(SalesMembers findMember, String accessToken, String refreshToken) {
        return LoginResDto.builder()
                .memberCode(findMember.getSalesMemberCode())
                .memberRank(findMember.getRank().toString())
                .memberTeam(findMember.getTeam() != null ?  findMember.getTeam().getTeamCode() : "")
                .name(findMember.getName())
                .email(findMember.getEmail())
                .image(findMember.getProfileImage())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public SalesMembers salesMemberCreateReqDtoToSalesMembers(SalesMemberCreateReqDto dto, Long salesMemberCode, String encodePassword) {
        SalesMembers member = SalesMembers.builder()
                .salesMemberCode(salesMemberCode)
                .email(dto.email())
                .phone(dto.phone())
                .name(dto.name())
                .password(encodePassword)
                .birthDay(dto.birthDay())
                .zoneCode(dto.zoneCode())
                .roadAddress(dto.roadAddress())
                .detailAddress(dto.detailAddress())
                .rank(dto.rank())
                .profileImage("https://aliothsss.s3.ap-northeast-2.amazonaws.com/member/defaultImage.jpg")
                .build();
        return member;
    }

    public SalesMemberResDto smToSmResDto(SalesMembers member){
        return SalesMemberResDto.builder()
                .rank(member.getRank())
                .salesMemberCode(member.getSalesMemberCode())
                .profileImage(member.getProfileImage() == null ? "" : member.getProfileImage())
                .birthDay(member.getBirthDay())
                .performanceReview(member.getPerformanceReview())
                .teamCode(member.getTeam() == null ? "NoTeam" : member.getTeam().getTeamCode())
                .teamName(member.getTeam() == null ? "NoTeam" : member.getTeam().getTeamName())
                .zoneCode(member.getZoneCode())
                .roadAddress(member.getRoadAddress())
                .detailAddress(member.getDetailAddress())
                .officeAddress(member.getOfficeAddress() == null ? "" : member.getOfficeAddress())
                .extensionNumber(member.getExtensionNumber() == null ? "" : member.getExtensionNumber())
                .phone(member.getPhone())
                .name(member.getName())
                .email(member.getEmail())
                .monthlyTargetCount(member.getMonthlyTargetCount())
                .monthlyTargetPrice(member.getMonthlyTargetPrice())
                .build();
    }


    // 팀
    public TeamResDto teamToTeamResDto(Team team,String teamManagerName){
        return TeamResDto.builder()
                .teamCode(team.getTeamCode())
                .teamName(team.getTeamName())
                .teamManagerName(teamManagerName)
                .performanceReview(team.getPerformanceReview() == null ? "" : team.getPerformanceReview())
                .monthlyTargetPrice(team.getMonthlyTargetPrice() == null ? null : team.getMonthlyTargetPrice())
                .monthlyTargetCount(team.getMonthlyTargetCount() == null ? null : team.getMonthlyTargetCount())
                .build();
    }
    public TeamResDto teamToTeamResDto(Team team, String teamManagerName, List<SalesMemberResDto> list){
        return TeamResDto.builder()
                .teamCode(team.getTeamCode())
                .teamName(team.getTeamName())
                .teamManagerName(teamManagerName)
                .teamMemberList(list)
                .performanceReview(team.getPerformanceReview() == null ? "" : team.getPerformanceReview())
                .monthlyTargetPrice(team.getMonthlyTargetPrice() == null ? null : team.getMonthlyTargetPrice())
                .monthlyTargetCount(team.getMonthlyTargetCount() == null ? null : team.getMonthlyTargetCount())
                .build();
    }

    public Team teamCreateDtoToTeam(TeamReqDto dto, String teamCode){
        return Team.builder()
                .teamCode(teamCode)
                .teamName(dto.teamName())
                .teamManagerCode(dto.teamManagerCode())
                .performanceReview(dto.performanceReview() == null ? "" : dto.performanceReview())
                .monthlyTargetPrice(dto.monthlyTargetPrice() == null ? null : dto.monthlyTargetPrice())
                .monthlyTargetCount(dto.monthlyTargetCount() == null ? null : dto.monthlyTargetCount())
                .build();
    }


    // 계약
    public Contract ContractCreateDtoToContract(String contractCode,ContractCreateDto dto, ContractMembers contractMembers, Custom custom, InsuranceProduct insuranceProduct, SalesMembers salesMember) {
        return Contract.builder()
                .contractCode(contractCode)
                .contractDate(dto.contractDate())
                .contractExpireDate(dto.contractExpireDate())
                .contractPeriod(dto.contractPeriod())
                .contractTotalPrice(dto.contractTotalPrice())
                .contractPaymentAmount(dto.contractPaymentAmount())
                .contractPaymentFrequency(dto.contractPaymentFrequency())
                .contractPaymentMaturityInstallment(dto.contractPaymentMaturityInstallment())
                .contractCount(dto.contractCount())
                .contractPaymentMethod(dto.contractPaymentMethod())
                .contractPayer(dto.contractPayer())
                .contractConsultation(dto.contractConsultation())
                .contractStatus(dto.contractStatus())
                .contractMembers(contractMembers)
                .custom(custom)
                .insuranceProduct(insuranceProduct)
                .salesMembers(salesMember)
                .build();
    }

    public ContractResDto ContractToContractResDto(Contract contract) {
        return ContractResDto.builder()
                .contractId(contract.getContractId())
                .contractCode(contract.getContractCode())
                .contractDate(contract.getContractDate())
                .contractExpireDate(contract.getContractExpireDate())
                .contractPeriod(contract.getContractPeriod())
                .contractTotalPrice(contract.getContractTotalPrice())
                .contractPaymentAmount(contract.getContractPaymentAmount())
                .contractPaymentFrequency(contract.getContractPaymentFrequency())
                .contractPaymentMaturityInstallment(contract.getContractPaymentMaturityInstallment())
                .contractCount(contract.getContractCount())
                .contractPaymentMethod(contract.getContractPaymentMethod())
                .contractPayer(contract.getContractPayer())
                .contractConsultation(contract.getContractConsultation())
                .contractStatus(contract.getContractStatus())
                .insuranceProductName(contract.getInsuranceProduct() != null ? contract.getInsuranceProduct().getInsuranceName() : null)
                .customName(contract.getCustom() != null ? contract.getCustom().getCustomerName() : null)
                .contractMemberName(contract.getContractMembers() != null ? contract.getContractMembers().getCM_name() : null)
                .salesMemberName(contract.getSalesMembers() != null ? contract.getSalesMembers().getName() : null)  // 영업 사원 이름 추가
                .salesMemberCode(contract.getSalesMembers() != null ? contract.getSalesMembers().getSalesMemberCode() : null)    // 영업 사원 ID 추가
                .salesMemberResDto(contract.getSalesMembers() != null ? this.smToSmResDto(contract.getSalesMembers()) : null)
                .build();
    }

    public ContractExcelResDto ContractResDtoTOcontractExcelResDto(ContractResDto contractResDto){
        return ContractExcelResDto.builder()
                .contractId(contractResDto.contractId())
                .contractCode(contractResDto.contractCode())
                .contractDate(contractResDto.contractDate())
                .contractExpireDate(contractResDto.contractExpireDate())
                .contractPeriod(contractResDto.contractPeriod())
                .contractTotalPrice(contractResDto.contractTotalPrice())
                .contractPaymentAmount(contractResDto.contractPaymentAmount())
                .contractPaymentFrequency(contractResDto.contractPaymentFrequency())
                .contractPaymentMaturityInstallment(contractResDto.contractPaymentMaturityInstallment())
                .contractCount(contractResDto.contractCount())
                .contractPaymentMethod(contractResDto.contractPaymentMethod())
                .contractPayer(contractResDto.contractPayer())
                .contractConsultation(contractResDto.contractConsultation())
                .contractStatus(contractResDto.contractStatus())
                .insuranceProductName(contractResDto.insuranceProductName())
                .customName(contractResDto.customName())
                .contractMemberName(contractResDto.contractMemberName())
                .salesMemberName(contractResDto.salesMemberName())
                .salesMemberCode(contractResDto.salesMemberCode())
                .build();
    }


    // 일정
    public Schedule ScheduleCreateDtoToSchedule(ScheduleReqDto scheduleReqDto, SalesMembers salesMembers){
        return Schedule.builder()
                .scheduleStartTime(scheduleReqDto.scheduleStartTime())
                .scheduleTitle(scheduleReqDto.scheduleTitle())
                .scheduleEndTime(scheduleReqDto.scheduleEndTime())
                .scheduleNote(scheduleReqDto.scheduleNote())
                .scheduleType(scheduleReqDto.scheduleType())
                .share(scheduleReqDto.share())
                .color(scheduleReqDto.color())
                .allDay(scheduleReqDto.allDay())
                .salesMembers(salesMembers)
                .build();
    }

    public ScheduleResDto ScheduleToScheduleResDto(Schedule schedule){
        return ScheduleResDto.builder()
                .scheduleId(schedule.getScheduleId())
                .scheduleTitle(schedule.getScheduleTitle())
                .scheduleStartTime(schedule.getScheduleStartTime())
                .scheduleEndTime(schedule.getScheduleEndTime())
                .scheduleNote(schedule.getScheduleNote())
                .scheduleType(schedule.getScheduleType())
                .share(schedule.getShare())
                .color(schedule.getColor())
                .allDay(schedule.getAllDay())
                .del_yn(schedule.getScheduleDel_YN())
                .memberId(schedule.getSalesMembers().getSalesMemberCode())
                .build();
    }


    // 게시판
    public BoardResDto BoardToBoardResDto(Board board){
        return BoardResDto.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .boardType(board.getBoardType())
                .writerName(board.getSalesMembers().getName())
                .salesMemberCode(board.getSalesMembers().getSalesMemberCode())
                .created_at(board.getCreatedAt())  // 날짜 필드 추가
                .updated_at(board.getUpdatedAt())
                .build();
    }



    public Board BoardCreateDtoToBoard(BoardCreateDto boardCreateDto, SalesMembers salesMembers){
        return Board.builder()
                .title(boardCreateDto.title())
                .content(boardCreateDto.content())
                .boardType(boardCreateDto.boardType())
                .salesMembers(salesMembers)
                .build();
    }


    // 답변
    public AnswerResDto AnswerToAnswerResDto(Answer answer){
        return AnswerResDto.builder()
                .answer_id(answer.getAnswerId())
//                .title(answer.getTitle())
                .content(answer.getContent())
                .created_at(answer.getCreatedAt())
                .salesMemberCode(answer.getSalesMembers().getSalesMemberCode())
                .answer_name(answer.getSalesMembers().getName())
                .build();
    }

    public Answer AnswerReqToAnswer(AnswerReqDto answerReqDto, SalesMembers salesMembers, Board board){
        return Answer.builder()
                .content(answerReqDto.content())
                .salesMembers(salesMembers)
                .board(board)
                .build();
    }


}
