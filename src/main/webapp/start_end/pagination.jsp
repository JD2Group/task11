<table>
    <tr>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="<%=DEFAULT_FIRST_PAGE_NUMBER%>" name="currentPage">
                <button class="btn btn-light" type="submit">First</button>
            </form>
        </td>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="<%=currentPage-1%>" name="currentPage">
                <button class="btn btn-outline-secondary" type="submit">Previous</button>
            </form>
        </td>
        <td>
            <p><%=currentPage%>
            </p>
        </td>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="<%=currentPage+1%>" name="currentPage">
                <button class="btn btn-outline-primary" type="submit">Next</button>
            </form>
        </td>
        <td>
            <form action="<%=action%>" method="get">
                <input type="hidden" value="<%=DEFAULT_LAST_PAGE_NUMBER%>" name="currentPage">
                <button class="btn btn-light" type="submit">Last</button>
            </form>
        </td>
    </tr>
</table>