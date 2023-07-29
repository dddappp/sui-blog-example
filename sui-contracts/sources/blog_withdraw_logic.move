module sui_blog_example::blog_withdraw_logic {
    use sui::balance::Balance;
    use sui::sui::SUI;
    use sui::tx_context;
    use sui::tx_context::TxContext;

    use sui_blog_example::blog;
    use sui_blog_example::vault_withdrawn;

    friend sui_blog_example::blog_aggregate;

    public(friend) fun verify(
        amount: u64,
        blog: &blog::Blog,
        ctx: &TxContext,
    ): blog::VaultWithdrawn {
        let _ = ctx;
        assert!(@admin_addr == tx_context::sender(ctx), 111);
        blog::new_vault_withdrawn(
            blog,
            amount,
        )
    }

    public(friend) fun mutate(
        vault_withdrawn: &blog::VaultWithdrawn,
        blog: &mut blog::Blog,
        ctx: &TxContext, // modify the reference to mutable if needed
    ): Balance<SUI> {
        let amount = vault_withdrawn::amount(vault_withdrawn);
        let _ = ctx;
        let balance = blog::borrow_mut_vault(blog);
        let w = sui::balance::split(balance, amount);
        w
    }
}
