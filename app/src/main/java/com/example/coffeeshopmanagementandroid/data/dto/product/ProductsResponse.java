package com.example.coffeeshopmanagementandroid.data.dto.product;

import com.example.coffeeshopmanagementandroid.domain.model.ProductModel;

import java.util.List;

public class ProductsResponse {

    private List<ProductModel> data;
    private Paging paging;

    public ProductsResponse() {
    }

    // Getter và Setter cho data
    public void setData(List<ProductModel> data) {
        this.data = data;
    }

    public List<ProductModel> getData() {
        return data;
    }

    // Getter và Setter cho paging
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Paging getPaging() {
        return paging;
    }

    public static class Paging {
        private int page;
        private int limit;
        private int total;
        private int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
