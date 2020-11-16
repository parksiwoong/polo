//상품수량 변경
let $quantity = $('.quantity'),
    $unitprice = $quantity.attr('data-unitprice'), //데이타유니프라이스안에 값을 유니프라이스로 잡는다
    $qtyBtn = $quantity.find('span'),  //컨텐트안에 스펜태그를 클랙하면 할일~
    $qytInput = $quantity.find('input'), //인풋 수자를 바꾼다음
    $tagetTotal = $('.total_price .price'); //토탈프라이스의 프라이트 변수도 바꿔야함

/*
* $qtyBtn을  클릭하면 그 요소가 class명 prev 있다면
* 참이면( +를 클릭했다면)
* $qtyBtn value 기존값에서 1증가.
* 거짓이면 - 증가
* */
$qtyBtn.click(function (){
    let currentCount = $qytInput.val()
    if ($(this).hasClass('plus')){
        $qytInput.val(++currentCount);
    }else {
        if(currentCount > 1){
            $qytInput.val(--currentCount);
    }}
    //수량*단가 변수 total에 저장하고 그걸 .price 값으로 변경되도록한다.
    var total = currentCount *  $unitprice + '$';
    $tagetTotal.text(total);
});