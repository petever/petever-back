package com.example.petever.domain.notion.enumuration;

public enum NotionAPi {

    DATA_BASE("/v1/databases/{id}/query"),
    PAGE("/v1/pages/{page_id}"),
    PAGE_PROPERTIES("/v1/pages/:page_id/properties/{property_id}"),
    BLOCK("/v1/blocks/{block_id}"),
    BLOCK_CHILDREN("/v1/blocks/{block_id}/children");

    private String endPoint;
    private final String URL = "https://api.notion.com";

    NotionAPi(String endPoint) {
        this.endPoint = URL + endPoint;
    }

    public String endPoint() {
        return endPoint;
    }
}
