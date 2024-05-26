export interface PageResponsse<DTO> {
    content: DTO[];
    number: number;
    totalPage: number;
    size:number;
}