package com.active.dbtermproject.service;

import com.active.dbtermproject.domain.Reservation;
import com.active.dbtermproject.repository.ReservationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.Date;

@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;

    //예약 추가
    /**
     *
     * @param reservation : reservation.customer_id,reservation.isbn,reservation.reserv_date
     * @return
     */
    public int insertReservation(Reservation reservation) { return this.reservationDao.insert(reservation);}

    //예약 취소
    /**
     *
     * @param reservation : reservation.isbn
     * @return
     */
    public int cancleReservation(Reservation reservation) {return this.reservationDao.delete(reservation);}

    //각 회원당 예약목록 조회
    /**
     *
     * @param customerId : user_id
     * @return
     */
    public List<Reservation> getReservationsByCustomerId(String customerId) {return this.reservationDao.getReservationsByCustomerId(customerId);}

    //주어진 isbn을 예약한 인원 수
    /**
     *
     * @param reservation : reservation.isbn
     * @return
     */
    public int countReservationByIsbn(Reservation reservation){return this.reservationDao.countReservationByIsbn(reservation);}

    //주어진 isbn이 예약된 목록들 반환
    /**
     *
     * @param reservation : reservation.isbn
     * @return
     */
    public List<Reservation> whoReservedPerIsbn(Reservation reservation){return this.reservationDao.getAllReservByIsbn(reservation);}

    /**
     *
     * @param reservation : reservation.isbn
     * @return : 대출 가능한 날짜(YYYY-MM-DD)
     */
    public Date possibleBorrowDate(Reservation reservation){
        return this.reservationDao.availableDate(reservation);
    }
}
