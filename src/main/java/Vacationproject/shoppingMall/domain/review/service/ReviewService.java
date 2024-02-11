package Vacationproject.shoppingMall.domain.review.service;

import Vacationproject.shoppingMall.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void notWrittenReviews(long userId) {
    }
}
