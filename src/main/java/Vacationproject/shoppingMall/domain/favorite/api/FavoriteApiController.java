package Vacationproject.shoppingMall.domain.favorite.api;

import Vacationproject.shoppingMall.domain.favorite.service.FavoriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.TAG_FAVORITE;
import static Vacationproject.shoppingMall.common.constant.SwaggerConstants.TAG_FAVORITE_DESCRIPTION;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@Tag(name = TAG_FAVORITE, description = TAG_FAVORITE_DESCRIPTION)
public class FavoriteApiController {
    private final FavoriteService favoriteService;

//    @GetMapping
//    public ApiResponse<List<UserFavoriteProductResponse>> getUserFavorite(
//            //@AuthenticationPrincipal PrincipalDetails principalDetails
//    ) {
//        Long userId = 1L;
//        List<UserFavoriteProductResponse> productResponses = favoriteService.getUserFavorite(userId);
//    }
//
//    @PostMapping
//    public ApiResponse<FavoriteMessage> addFavorite(
//            //@AuthenticationPrincipal PrincipalDetails principalDetails
//            @RequestParam(name = PRODUCT_ID, defaultValue = PRODUCT_ID_DESCRIPTION) Long productId
//    ) {
//        Long userId = 1L;
//        FavoriteMessage favoriteMessage = favoriteService.addFavorite(userId, productId);
//
//        return success(favoriteMessage);
//    }
}
