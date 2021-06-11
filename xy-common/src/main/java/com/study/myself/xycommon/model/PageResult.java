package com.study.myself.xycommon.model;

import java.io.Serializable;
import java.util.List;

/**
 * @program: xy-parent
 * @description: 分页
 * @author: wxy
 * @create: 2021-05-10 14:08
 **/
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private long size;
    private long pages;
    private long current;
    private List<T> records;

    public PageResult(List<T> records, long total, long size, long current) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
        this.pages = this.calculatePages(total, size);
    }

    // public PageResult(Page<T> page) {
    //     this.records = page.getRecords();
    //     this.total = page.getTotal();
    //     this.size = page.getSize();
    //     this.current = page.getCurrent();
    //     this.pages = page.getPages();
    // }

    private long calculatePages(long total, long size) {
        if (size == 0L) {
            return 0L;
        } else {
            long pages = total / size;
            if (total % size != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    public long getTotal() {
        return this.total;
    }

    public long getSize() {
        return this.size;
    }

    public long getPages() {
        return this.pages;
    }

    public long getCurrent() {
        return this.current;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setTotal(final long total) {
        this.total = total;
    }

    public void setSize(final long size) {
        this.size = size;
    }

    public void setPages(final long pages) {
        this.pages = pages;
    }

    public void setCurrent(final long current) {
        this.current = current;
    }

    public void setRecords(final List<T> records) {
        this.records = records;
    }

    // public boolean equals(final Object o) {
    //     if (o == this) {
    //         return true;
    //     } else if (!(o instanceof PageResult)) {
    //         return false;
    //     } else {
    //         PageResult<?> other = (PageResult)o;
    //         if (!other.canEqual(this)) {
    //             return false;
    //         } else if (this.getTotal() != other.getTotal()) {
    //             return false;
    //         } else if (this.getSize() != other.getSize()) {
    //             return false;
    //         } else if (this.getPages() != other.getPages()) {
    //             return false;
    //         } else if (this.getCurrent() != other.getCurrent()) {
    //             return false;
    //         } else {
    //             Object this$records = this.getRecords();
    //             Object other$records = other.getRecords();
    //             if (this$records == null) {
    //                 if (other$records != null) {
    //                     return false;
    //                 }
    //             } else if (!this$records.equals(other$records)) {
    //                 return false;
    //             }
    //
    //             return true;
    //         }
    //     }
    // }
    //
    // protected boolean canEqual(final Object other) {
    //     return other instanceof PageResult;
    // }
    //
    // public int hashCode() {
    //     int PRIME = true;
    //     int result = 1;
    //     long $total = this.getTotal();
    //     int result = result * 59 + (int)($total >>> 32 ^ $total);
    //     long $size = this.getSize();
    //     result = result * 59 + (int)($size >>> 32 ^ $size);
    //     long $pages = this.getPages();
    //     result = result * 59 + (int)($pages >>> 32 ^ $pages);
    //     long $current = this.getCurrent();
    //     result = result * 59 + (int)($current >>> 32 ^ $current);
    //     Object $records = this.getRecords();
    //     result = result * 59 + ($records == null ? 43 : $records.hashCode());
    //     return result;
    // }
    //
    public String toString() {
        return "PageResult(total=" + this.getTotal() + ", size=" + this.getSize() + ", pages=" + this.getPages() + ", current=" + this.getCurrent() + ", records=" + this.getRecords() + ")";
    }

    public PageResult() {
    }
}
