package Vacationproject.shoppingMall.domain.favorite.service;

import Vacationproject.shoppingMall.domain.favorite.repository.FavoriteRepository;
import Vacationproject.shoppingMall.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductService productService;
//    private final UserService userService;

    /**
     * 1. FavoriteList를 가져올때 Product를 fetchJoin으로 가져오기
     * 2. Product 가져올때 ProductImage fetchJoin으로 가져오기 or
     * Product와 Image 관계는 EAGER로 바꾸는 것도 괜찮지 않나? -> 왜냐하면 Product를 Response하는 모든 경우에서 Image가 포함됨
     */
//    public List<UserFavoriteProductResponse> getUserFavorite(Long userId) {
//        List<Favorite> favorites = userService.getFavorite(userId);
//        List<UserFavoriteProductResponse> productResponses = favorites.stream().map(
//                favorite -> UserFavoriteProductResponse.of(favorite.getProduct()))
//                .toList();
//        return productResponses;
//    }


}
