let url = new URL(window.location.href);
let result = url.searchParams.get("result");
if (result !== null) {
    if (result === "no_matching_id") {
        alert("존재하지 않는 게시판입니다.");
        url.searchParams.delete("result");
        // window.location.href = url;
    } else if (result === "not_allowed") {
        alert("게시글을 작성할 권한이 없습니다.");
        window.history.back();
    }
}loginForm.onsubmit = function (){
    let titleInput = loginForm.elements["title"];
    let writerInput = loginForm.elements["write"];

    if(titleInput.value === ""){
        alert("제목을 입력해주세요");

        titleInput.focus();

        return false;}
    else if(writerInput.value === ""){
        alert("내용을 입력해주세요");

    writerInput.focus();
    return false;}
    return true;}


let searchForm = window.document.body.querySelector("#search-form");
searchForm.onsubmit = function() {
    if (searchForm.elements["keyword"].value === "") {
        alert("검색어를 입력해주세요.");
        searchForm.elements["keyword"].focus();
        return false;
    } else {
        return true;
    }
}