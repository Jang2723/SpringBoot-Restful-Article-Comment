package org.example.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 실제로 게시글을 저장하고 생성하고 조회하는 클래스

// 일을 수행하는 역할
public class ArticleService {
    // 사용자의 입력을 받는 스캐너
    private final Scanner scanner;
    // 사용자가 작성한 데이터를 담기위한 리스트
    private final List<ArticleMem> articles;

    // 일을 할 준비단계
    public ArticleService(Scanner scanner) {
        this.scanner = scanner;
        this.articles = new ArrayList<>();
    }

    // 1. 게시글을 작성해, articles에 저장하는 메서드
    public void writeArticle() {
        // 제목을 입력받는다.
        System.out.print("제목을 입력하세요: ");
        String newTitle = this.scanner.nextLine();
        // 본문을 입력받는다.
        System.out.print("본문을 입력하세요: ");
        String newContent = this.scanner.nextLine();
        // 게시글 목록에 저장한다.
        ArticleMem newArticle =
                new ArticleMem(newTitle, newContent);
        this.articles.add(newArticle);
    }

    // 2. 작성된 게시글들의 제목을 나열하는 메서드
    public void showAllTitles() {
        // 모든글을 순회할 것인데,
        // 사용자가 하나 보기에서 어떤 숫자를 넣을지를 판단할 수 있게
        // 하기 위하여, i를 같이 출력해준다.
        for (int i = 0; i < this.articles.size(); i++) {
            // i번째 게시글을 가져온다.
            ArticleMem article = this.articles.get(i);
            // `번호. 제목`형태로 출력한다.
            System.out.println(
                    String.format("%d. %s", i, article.getTitle()));
        }
    }


    // 3. 하나의 게시글의 정보를 출력하는 메서드
    public void readArticle() {
        // 사용자에게 몇번 글을 읽을건지 물어보고,
        System.out.print("글 번호를 선택하세요: ");
        String idxString = this.scanner.nextLine();
        int idx = Integer.parseInt(idxString);

        // 제목: {제목}
        // 내용: {내용}
        ArticleMem article = this.articles.get(idx);
        System.out.println();
        System.out.println(String.format("제목: %s", article.getTitle()));
        System.out.println(String.format("내용: %s", article.getContent()));
    }
}
